package der.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yaml.snakeyaml.Yaml;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dev2 on 2018/7/9.
 */
public class DataRecord {
    static String baseUrl = "http://218.244.150.127:18880";

    static String sessionId = null;

    public static void main(String[] args) throws IOException {
        //第一次请求，拿到sessionId,验证码图片url
        Connection connection = Jsoup.connect(baseUrl+"/bap/index");
        Connection.Response response = connection.method(Connection.Method.GET).execute();
        sessionId = response.cookie("JSESSIONID");
        Document parse = response.parse();
        Element codeimg = parse.getElementById("codeimg");
        String imgUrl = baseUrl+codeimg.attr("src");       //验证码url

        String loginUrl = baseUrl+"/bap/login/check";


        //登录信息
        Map loginInfo = new HashMap<String, String>();

        Yaml yaml =new Yaml();
        URL resourceUrl = DataRecord.class.getClassLoader().getResource("biddata.yml");
        String username = "";
        String password = "";
        if (resourceUrl!=null){
            Map map = (Map) yaml.load(new FileInputStream(resourceUrl.getFile()));
            username = map.get("username").toString();
            password = map.get("password").toString();
        }

        loginInfo.put("username",username);
        //base64加密
        String newKey=(new BASE64Encoder()).encodeBuffer(password.getBytes());
        loginInfo.put("sp",newKey);

        Connection loginCon = Jsoup.connect(loginUrl);
        loginInfo.put("code",getCode(imgUrl));//在这里新起一个连接下载验证码图片，并手动输入
        Connection.Response loginResponse = loginCon.method(Connection.Method.POST).cookie("JSESSIONID",sessionId).data(loginInfo).execute();

        sessionId = loginResponse.cookie("JSESSIONID");

        //获取渠道数据
        int channelCount = getChannelData();


        HashMap<String, Integer> bidData = getBidData();

        int expectedTotalBid = bidData.get("expectedTotalBid");
        int actualTotalBid = bidData.get("actualTotalBid");

        String timeStr = new SimpleDateFormat("HH:mm    MM月dd日").format(new Date());
        System.out.println(timeStr);
        System.out.println("满标金额："+expectedTotalBid);
        System.out.println("成交金额："+actualTotalBid);
        System.out.println("渠道金额："+channelCount);

    }

    public static String getCode(String imgUrl) throws IOException {

        Connection connect = Jsoup.connect(imgUrl);

        Connection.Response response = connect.ignoreContentType(true).cookie("JSESSIONID",sessionId).method(Connection.Method.GET).execute();

        byte[] bytes = response.bodyAsBytes();

        String directory = "D:\\codeImage";

        //获取图片
        if (!new File(directory).exists()){
            new File(directory).mkdir();
        }
        OutputStream os = new FileOutputStream(new File(directory+"\\code.jpg"));
        os.write(bytes);
        os.close();

        //输入验证码
        System.out.println("输入验证码");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();

        scanner.close();

        return code;
    }


    //今日渠道总数
    public static int getChannelData() throws IOException {
        String channnelUrl = baseUrl+"/bap/ChannelInvestRecord/list";
        Connection channelConnect = Jsoup.connect(channnelUrl);


        String date =new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //请求参数
        Map requestBody = new HashMap<String,String>();
        requestBody.put("pageIndex",1+"");
        requestBody.put("totalCount",0+"");
        requestBody.put("investTimeStart",date);
        requestBody.put("investTimeEnd",date);

        Connection.Response response = channelConnect.method(Connection.Method.POST).cookie("JSESSIONID", sessionId).data(requestBody).execute();

        Document document = response.parse();
        //获取渠道总数
        String data = document.select("tbody").select("tr").select("span").text();
        //处理格式
        String channelCount = data.replaceAll(",", "").split("\\.")[0];

        return Integer.valueOf(channelCount);
    }

    //满标+投标

    public static HashMap<String,Integer> getBidData() throws IOException {
        HashMap bidData = new HashMap<String,Integer>();
        String bidUrl = baseUrl+"/bap/DailyBidDataStatistic/list";
        Connection connection = Jsoup.connect(bidUrl);
        String date =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Connection.Response response = connection.method(Connection.Method.POST).cookie("JSESSIONID", sessionId).data("date", date).execute();
        Document document = response.parse();
        //获取表单对象
        Elements tbody = document.select("tbody");

        //车、房、农、分期投标
        String actualCarBidStr = tbody.select("tr").first().child(3).text().replaceAll(",","");
        String actualHousingBidStr = tbody.select("tr").first().child(5).text().replaceAll(",","");
        String actualAgriculturalBidStr = tbody.select("tr").first().child(7).text().replaceAll(",","");
        String actualInstalmentBidStr = tbody.select("tr").first().child(9).text().replaceAll(",","");

        //投标总额
        int actualTotalBid = getString2IntSum(actualCarBidStr,actualHousingBidStr,actualAgriculturalBidStr,actualInstalmentBidStr);
        bidData.put("actualTotalBid",actualTotalBid);


        //满标总额
        int expectedTotalBid = 0;
        //获取期限1个月的满标
        String oneMonthCarBidStr = tbody.select("tr").first().child(2).text().replace(",","");
        String oneMonthHousingBidStr = tbody.select("tr").first().child(4).text().replace(",","");
        String oneMonthAgriculturalBidStr = tbody.select("tr").first().child(6).text().replace(",","");
        String oneMonthInstalmentBidStr = tbody.select("tr").first().child(8).text().replace(",","");

        expectedTotalBid += getString2IntSum(oneMonthCarBidStr,oneMonthHousingBidStr,oneMonthAgriculturalBidStr,oneMonthInstalmentBidStr);

        //获取3、6、9、12、15、18六个月的综合
        for (int i = 3;i<=18;i+=3){
            String carBidStr = tbody.select("tr").get(i / 3).child(1).text().replace(",", "");
            String housingBidStr = tbody.select("tr").get(i / 3).child(2).text().replace(",", "");
            String agriculturalBidStr = tbody.select("tr").get(i / 3).child(3).text().replace(",", "");
            String instalmentBidStr = tbody.select("tr").get(i / 3).child(4).text().replace(",", "");

            expectedTotalBid += getString2IntSum(carBidStr,housingBidStr,agriculturalBidStr,instalmentBidStr);
        }

        bidData.put("expectedTotalBid",expectedTotalBid);
        return bidData;
    }

    private static int getString2IntSum(String carBidStr,String housingBidStr,String agriculturalBidStr,String instalmentBidStr){
        //转int
        int carBid = "".equals(carBidStr)?0:Integer.valueOf(carBidStr);
        int housingBid = "".equals(housingBidStr)?0:Integer.valueOf(housingBidStr);
        int agriculturalBid = "".equals(agriculturalBidStr)?0:Integer.valueOf(agriculturalBidStr);
        int instalmentBid = "".equals(instalmentBidStr)?0:Integer.valueOf(instalmentBidStr);
        //求和
        return carBid+housingBid+agriculturalBid+instalmentBid;
    }


}
