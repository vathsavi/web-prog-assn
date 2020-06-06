<%@ page import="java.io.*,java.util.*" %>
<html>
    <head>
        <title>Count visitor</title>
    </head>
    <body>
        <form>
            
                
                <%
                    Integer hitsCount =
                    (Integer)application.getAttribute("hitCounter");
                    if( hitsCount ==null || hitsCount == 0 )
                    {
                        /* First visit */
                        out.println("Welcome to my website!!");
                        hitsCount = 1;
                    }
                    else
                    {
                        /* return visit */
                        out.println("Welcome to my website vathsavi!!");
                        hitsCount += 1;
                    }
                    application.setAttribute("hitCounter", hitsCount);
                %>
                <p>You are visitor number: <%= hitsCount%></p>
            
        </form>
    </body>
</html>