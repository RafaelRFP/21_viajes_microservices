package init;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
Usuarios/roles:

test1/test1 -> TEST
test2/test2 -> TEST
test3/test3 -> MASTER
test4/test4 -> USERS

Acceso a recursos:
-Altas y modificaciones solo rol MASTER
-Búsqueda de libros por temática solo usuarios autenticados
-Eliminaciones roles MASTER y USERS
*/


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* CREA USUARIOS Y ROLES SIN ACCESO A LA BASE DE DATOS */
		/*
		auth
        .inMemoryAuthentication()
        .withUser("test1")
          .password("{noop}test1") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
          .roles("TEST")
          .and()
        .withUser("test2")
          .password("{noop}test2") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
          .roles("TEST")
          .and()  
        .withUser("test3")
          .password("{noop}test3")
          .roles("MASTER")
          .and()
        .withUser("test4")
          .password("{noop}test4")
          .roles("USERS");
		*/
		
		/*lo siguiente sería para el caso de que
		 * quisiéramos encriptar la password:  
		String pw1=new BCryptPasswordEncoder().encode("user1");
		System.out.println(pw1);
		  auth
	        .inMemoryAuthentication()
	        .withUser("user1")
	          .password("{bcrypt}"+pw1)
	          //.password(pw1)
	          .roles("USER")
	          .and()
	        .withUser("admin")
	          .password("{bcrypt}"+new BCryptPasswordEncoder().encode("admin"))
	          .roles("USER", "ADMIN");
		 */
		
		/* ACCESO A USUARIOS, CLAVES Y ROLES MEDIANTE JDBC A LA BASE DE DATOS springsecurity */ 
		/*la siguiente configuración será para el caso de 
		 * usuarios en una base de datos */
		  
		auth.jdbcAuthentication().dataSource(dataSource())
        	.usersByUsernameQuery("select user, pwd, enabled "
            	+ "from users where user=?")
        	.authoritiesByUsernameQuery("select user, rol "
            	+ "from roles where user=?");
		 
	}

	//aquí definimos las politicas de acceso a recursos
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		//solo los miembros del rol admin podrán realizar altas
		
		.antMatchers(HttpMethod.GET,"/ListarReservas").hasRole("MASTER")
		
		//.antMatchers(HttpMethod.POST,"/Alta").hasRole("MASTER")
		//.antMatchers(HttpMethod.PUT,"/Modifica").hasRole("MASTER")
		//MAEJOR CON hasAnyRol
		//.antMatchers(HttpMethod.DELETE,"/Baja").hasRole("MASTER")
		//.antMatchers(HttpMethod.DELETE,"/Baja").hasRole("USERS")
		//.antMatchers(HttpMethod.DELETE,"/Baja").hasAnyRole("MASTER","USERS")
		
		
		//ojo poniendo 1* será poner algo, 2** será poner cualquier cosa
		//.antMatchers("/BuscarLibros/**").authenticated()
		//.antMatchers("/BuscarTema/*").authenticated()
		
		//.antMatchers("/ListarReservas").authenticated()
		.and()
		.httpBasic();
	}
	
	//no le ponemos la anotación @Bean para que no sea gestionado por spring porque chocaría con el 
	//datasource interno de spring
	//lo dejamos como un objeto privado porque solo lo vamos a utilizar únicamente en esta clase
	//crea un objeto con el nombre del identificador del método dataSource o como le llames
	private DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3346/springsecurity?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("rootroot");
		return ds;
	}

}
