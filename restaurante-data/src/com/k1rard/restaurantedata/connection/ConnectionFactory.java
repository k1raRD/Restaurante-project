/**
 * 
 */
package com.k1rard.restaurantedata.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author KiraRD.
 * Clase que se encarga de realizar la configuracion, la conexion y habilitar las transacciones
 * de una base de datos
 */
public class ConnectionFactory {
	/**
	 * Objeto encargado de mantener la conexion de la base de datos 
	 */
	private static Connection connection;
	/**
	 * Objeto encargado de habilitar y ejecutar las sentencias SQL
	 */
	private static Statement statement;
	
	/**
	 *  Metodo que permite conectarse a la base de datos
	 * @return Objeto Connection con la informacion de la base de datos conectada | null
	 * @throws ClassNotFoundException Excepcion generada al no cargar el driver de conexion
	 * @throws SQLException Excepcion generada al no conectarse a la base de datos
	 */
	
	public static Connection conectar() throws ClassNotFoundException, SQLException
	{
		// Paso 1: Cargar el driver de conexion a la base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Paso2: Establecer datos de conexion
		String url = "jdbc:mysql://127.0.0.1:3306/restaurante";
		String user = "root";
		String password = "Admin";
		
		//paso 3: Establecer la conexion
		
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement(); // Habilitando procesos para ejecutar sentencias SQL
		
		return connection;
	}
	
	/**
	 * Permite ejecutar sentecias INSERT, UPDATE y DELETE.
	 * @param sql Parametro con la sentencia a ejecutarse.
	 * @return 1 en caso de ser exitoso, 0 en caso de ser un error.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	public static int ejecutarSQL(String sql) throws SQLException
	{
		System.out.println("Query: " + sql);
		return statement.executeUpdate(sql);
	}
	/**
	 * Permite ejecutar sentencias SELECT
	 * @param sql Parametro con la sentencia a ejecutarse.
	 * @return Un objeto con la informacion de la sentencia SELECT
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	public static ResultSet ejecutarSQLSelect(String sql) throws SQLException
	{
		System.out.println("Query: " + sql);
		return statement.executeQuery(sql);
	}
}
