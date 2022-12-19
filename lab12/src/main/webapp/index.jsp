<%@ page isELIgnored="false" %>
  <html lang="en">

  <head>
    <meta charset="utf-8">
    <title>Servlet Calculator</title>
  </head>

  <body>
    <h1>Servlet Calculator
    </h1>
    <form action="sum" method="post">
      <p>
        Number a: <input type="number" name="a" required />
      </p>
      <p>
        Number b: <input type="number" name="b" required />
      </p>
      <p>
        <input type="submit" value="a+b=?" />
      </p>
    </form>
    <br />
  </body>

  </html>

<!-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <title>Calculator</title>
  </head>

  <body>
    <h1>Web Calculator
    </h1>
    <form action="sum" method="post">
      <p>
        Number a: <input type="number" name="a" required />
      </p>
      <p>
        Number b: <input type="number" name="b" required />
      </p>
      <p>
        <input type="submit" value="a+b=?" />
      </p>
    </form>
    <br />
  </body>

  </html> -->