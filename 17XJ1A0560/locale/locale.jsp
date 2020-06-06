<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="javax.servlet.*,javax.servlet.http.* "%>
<%@ page import="java.text.NumberFormat,java.util.Date" %>
<%@ page import="java.text.DateFormat,java.util.Date" %>

<%
   //Get the client's Locale
   Locale locale = request.getLocale();
   String language = locale.getLanguage();
   String country = locale.getCountry();

   String title = "Locale Specific Dates";
   //Get the client's Locale
   String date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, locale).format(new Date( ));
  
   NumberFormat nft = NumberFormat.getCurrencyInstance(locale);
   String formattedCurr = nft.format(500);
%>
<html>
    <head>
        <title>Detecting Locale</title>
    </head>
    <body>
        <form>
            <fieldset style="width:70%; background-color:#FFB6C1; align:center">
                <center>
                    <h1>Detecting Locale</h1>
                </center>
                <p align="center">
                <%
                    out.println("Language : " + language  + "<br />");
                    out.println("Country  : " + country   + "<br />");
                %>
                </p>
                <div align="center">
                    <h1><% out.print(title); %></h1>
                    <p style="color:yellow">Date: <%  out.print(date); %></p>
                </div>
            </fieldset>
        </form>
    </body>
</html>