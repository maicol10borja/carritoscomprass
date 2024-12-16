<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 25/11/2024
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="models.*" %>
<%
    // Recupera el objeto 'carro' de la sesión actual. Si el carro no existe, el valor de 'carro' será null.
    Carro carro = (Carro) session.getAttribute("carro");
%>
<html>
<head>
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>

<%
    // Verifica si el 'carro' es nulo o si no tiene productos (es decir, si la lista de productos está vacía).
    if (carro == null || carro.getItems().isEmpty()) {
%>
<!-- Mensaje que se muestra si el carro está vacío -->
<p>Lo sentimos no hay productos en el carro de compras!</p>
<%
} else {
%>
<!-- Si el carro tiene productos, muestra una tabla con los detalles de cada producto -->
<table>
    <tr>
        <th>ID PRODUCTO</th>
        <th>NOMBRE</th>
        <th>PRECIO</th>
        <th>CANTIDAD</th>
        <th>TOTAL</th>
    </tr>

    <%
        // Recorre todos los productos en el carro y muestra los detalles en una fila de la tabla.
        for (ItemCarro item : carro.getItems()) {
    %>
    <tr>
        <td><%= item.getProductos().getIdProducto() %></td>
        <td><%= item.getProductos().getNombre() %></td>
        <td><%= item.getProductos().getPrecio() %></td>
        <td><%= item.getCantidad() %></td>
        <td><%= item.getSbtotal() %></td>
    </tr>
    <% } %>

    <!-- Muestra el total de la compra -->
    <tr>
        <td colspan="4" style="text-align: right">Total</td>
        <td><%= carro.getTotal() %></td>
    </tr>
</table>
<% } %>

<!-- Enlaces para seguir comprando o ir al inicio -->
<p><a href="<%= request.getContextPath() %>/productos">Seguir comprando</a></p>
<p><a href="<%= request.getContextPath() %>/index.html">Ir al inicio</a></p>

</body>
</html>

