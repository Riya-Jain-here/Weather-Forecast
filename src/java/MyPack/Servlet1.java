package MyPack;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Date;

public class Servlet1 extends HttpServlet {
    private static final long serialVersionUId=1L;
    
    public Servlet1(){
        super();
    }
   
    
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        response.getWriter().append("served at:").append(request.getContextPath());
    }
    
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        PrintWriter out=response.getWriter();
          //get city
          String name = request.getParameter("city");
          System.out.println(name);
          
          //api deployment
          String apiKey="insert_your_api_key_here";
          //api url 
          String apiURL="https://api.openweathermap.org/data/2.5/weather?q="+ name + "&appid=" + apiKey;
          try{
          //api integrate
          if(apiURL.contains(" ")){
          apiURL = apiURL.replace(" ", "%20");
          }
          URL url=new URL(apiURL);
          HttpURLConnection connection=(HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          
          //to read data 
          InputStream inpstream=connection.getInputStream();
          InputStreamReader inpstreamreader=new InputStreamReader(inpstream);
          
          //to store data
          StringBuilder builder=new StringBuilder();
          Scanner sc=new Scanner(inpstreamreader);
          while(sc.hasNext()){
              builder.append(sc.nextLine());
        }   
          sc.close();
          System.out.println(builder);
       
          //conersion from json to gson
          Gson gson=new Gson();
          JsonObject jsonobj=gson.fromJson(builder.toString(),JsonObject.class);
          //System.out.println(jsonobj);
          
          //to fetch date and time
          long datetime=jsonobj.get("dt").getAsLong()*1000;
          String date=new Date(datetime).toString();
          
          //fetch temperature 
          double tempkel=jsonobj.getAsJsonObject("main").get("temp").getAsDouble();
          int tempcel=(int)(tempkel - 273.15);
          
          // fetch humidity
          int humidity=jsonobj.getAsJsonObject("main").get("humidity").getAsInt();
          
          //fetch wind speed
           double windspeed=jsonobj.getAsJsonObject("wind").get("speed").getAsDouble();
           
          //weather condition
          String weathercond=jsonobj.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();   
  
          //to send on jsp page
          
          request.setAttribute("date",date);
          request.setAttribute("city",name);
          request.setAttribute("temperature",tempcel);
          request.setAttribute("humidity",humidity);
          request.setAttribute("windspeed",windspeed);
          request.setAttribute("weathercond",weathercond);
          request.setAttribute("weatherdata",builder.toString());
          
          connection.disconnect();         
          }
       catch(IOException e ){
           System.out.println(e);
       }
       //sending request to jsp page.
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }
}
