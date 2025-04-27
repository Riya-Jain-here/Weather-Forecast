<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result | InfiniteWeather</title>
         <link rel="icon" href="logo_weather.png">
        <style>

             body{
                 background-image: url("img1.jpg");
                 background-repeat: no-repeat;
                 background-attachment: fixed;
                 background-size: 100% 100%;
            }
            
            .text-block{
             color: white;
             margin-left:28%;
             position: absolute;
             top:5%;
             background-color: black;
             height: 80%;
             width: 35%;
             padding: 40px;
             opacity:.7;
             border-radius: 15px;
             border-color: transparent;
            }
            
            .image_weather{
             position:absolute;
             height:140px;
             top:170px;
             margin-left: 135px;
             margin-right: auto;
             width: 36%;
             display: block;
           }
           
           .image_humidity{
               position:absolute;
             height:50px;
             top:430px;
             margin-left:-6px;
             margin-right: auto;
             width: 20%;
             display: block;
           }
           
           .image_wind{
               position:absolute;
             height:60px;
             top:430px;
             margin-left: 245px;
             margin-right: auto;
             width:20%;
             display: block;
           }
           
           .text-humidity{
               margin-left: 100px;
           }
           
           .text-wind{
               top: 420px;
               margin-left: 200px;
           }
           
           .wind_humid_level{
               display: flex;
           }
           
        </style>
    </head>
     
    <body>
        
        <div class="text-block">
            <center><h1><b>${city}</b></h1></center> 
            <center><h3>${date}</h3></center> 
             <img src="weather.png" class="image_weather">
             <br><br><br><br><br><br><br><br>
             <center><h1><b>${temperature} &deg; C</b></h1></center>  
             
             <center><h2><i>${weathercond}</i></h2></center>
             
             <div class="wind_humid_level">  
                 <img src="humidity.png" class="image_humidity"> 
             <div class="text-humidity">
                 <p><b>${humidity}</b> <br><i>Humidity</i></p>
             </div>
             
           <img src="wind.png" class="image_wind"> 
           <div class="text-wind">
               <p><b>${windspeed} Km/h</b> <br> <i>Wind Speed</i></p>
                 </div> 
             </div>
        </div>
    </body>
</html>
