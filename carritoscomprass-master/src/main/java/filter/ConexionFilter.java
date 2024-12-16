package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import utils.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//IMPLEMENTAMNOS UNA ANOTACION ESTA NOTACION ME SIRVE PARA PODER UTILIZAR LA CONEXXION EN CUALQUIER PARTE DE MI APLICAC ION

@WebFilter("/*")
public class ConexionFilter implements Filter {

    /*
    * una clase gilter en java es un objeto que realiza un filtrado en la soliciudes
    * respuesta a un recurso.los filtyrtos se pueden ejecutar en servidores
    * web compatible con jakarta EE
    * los filtros intercertan solicitudes y respuestas de manera dinbamica para trasformarlos
    * a utilizar la informacion que  contine filtro
    * se rresliza en un metodo dofilter*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        * request: peticiones del cliante al servidor
        * response: respuesta del servidor
        * filterchain: es una clase filtro que representa el flujo del procesamiento llamando al metdod
        * chain.dofilter*(request,repomse),dentro de un filtro la solicitud
        al siguiente filtro a al recurso destino(sevlet a un jsp)
        * */
        HttpServletRequest req = (HttpServletRequest) request;
        try(Connection conm = Conexion.getConnection()){
            //verficamos si la conexion es automatica pra cada instrucion SQL
            if (conm.getAutoCommit()){
                //si esto desactivamos el auto conmin
                conm.setAutoCommit(false);
            }
            try {
                //agregamos la conexion como un atributo en la solicitud y esto permite que otros compónentes
                //puedan aceder a la conexion desde el opbjeto request
                request.setAttribute("conm", conm);
                //pasa la solicitud y la respuesta al siguiente filtro de recurso o destino  (servlet a jsp)
                chain.doFilter(request, response);
                //si el proceso pasa correctamente ain ninguna exepcion se confirma la solicitud
                //lanzar ninguna exepcoion se confima
                conm.commit();
                //si ocurre una exepcion se lanza dicha ecepcion y no se cmabia la base de dartos
                //se aplica los campos necesarios
            }catch (SQLException e){
                //se deshacer los cambios con rolblack y de eso forma se mantine
                //la integridad de los datos
                conm.rollback();
            }
        } catch (SQLException e){
            throw new ServletException(e);
        }

    }
}
