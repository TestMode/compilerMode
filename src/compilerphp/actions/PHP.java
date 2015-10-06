package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PHP{
	
	static String path_proyect;//RUTA DONDE ESTA EL CODIGO DEL PROYECTO PHP A GENERAR
	static String path_down_proyect;// RUTA UN NIVEL MÁS ABAJO DEL PROYECTO EN LOS DIRECTORIOS
	static SQL modelo;
	static String web_name;
	
	/*
	 * CONSTRUCTOR DE LA CLASE
	 *  path : direccion del proyecto PHP
	 */
	public PHP(String path, SQL model, String web_name_){
		path_proyect=path+"/PHP/"; // RUTA DEL PROYECTO PHP
		path_down_proyect=path;
		modelo=model;//MODELO DE LA BASE DE DATOS
		web_name=web_name_;
	}
	
	//DESPLIEGA EL CODIGO BASE PHP EN LA CARPETA DEL PROYECTO PHP
	public void start(){
		ExecuteShellComand obj= new ExecuteShellComand();
		//obj.clean_proyect(path_down_proyect);//en caso de existir un proyecto anterior lo respalda
		obj.start_proyect(path_proyect);
	}
	
	/*
	 * CONFIGURA LA BASE DE DATOS
	 * 	configuración para sqlite, si se desea otra BDD, esta desde el
	 *  panel de administración de la plataforma se puede cambiar.
	 */
	public void configureBD_Gii(String db_name){
		String config_db=path_proyect+"proyect/config/db.php";
		ExecuteShellComand obj= new ExecuteShellComand();
		obj.backup(config_db);
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(config_db);
			String sqlite_conf="<?php\n"
					+"return [\n"
					+"'class' => 'yii\\db\\Connection', \n"
					+"'dsn' => 'sqlite:config/"+db_name+"',\n"
					+"];";
			fichero.write(sqlite_conf);
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Mover la BDD
		obj.executeCommand("mv "+path_proyect+db_name+" "+path_proyect+"proyect/config/");
	}
	
	//EJECUTA YII PARA LA GENERACIÓN DE MODELOS , CONTROLADORES Y PAGINAS DE LOS SERVICIOS POR DEFECTO
	public void yiiExec(){
		Yii yii = new Yii(modelo, path_proyect);
		try {
			yii.model();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			yii.crud();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * CONFIGURA LA BASE DE DATOS
	 * 	configuración para sqlite, si se desea otra BDD, esta desde el
	 *  panel de administración de la plataforma se puede cambiar.
	 */
	public void configureBD_Apache(String db_name){
		String config_db=path_proyect+"proyect/config/db.php";
		ExecuteShellComand obj= new ExecuteShellComand();
		obj.backup(config_db);
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(config_db);
			String sqlite_conf="<?php\n"
					+"return [\n"
					+"'class' => 'yii\\db\\Connection', \n"
					+"'dsn' => 'sqlite:../config/"+db_name+"',\n"
					+"];";
			fichero.write(sqlite_conf);
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Mover la BDD
		obj.executeCommand("mv "+path_proyect+db_name+" "+path_proyect+"proyect/config/");
	}
	
	/*
	 * GENERA EL MODELO DE LAS TABLAS
	 */
	public void genModel(){
		ExecuteShellComand obj= new ExecuteShellComand();
		String comando="bash "+path_proyect+"model.sh "+path_proyect;
		obj.executeCommand(comando);
		//BORRAR BASH
	}
	
	/*
	 * GENERA LOS CONTROLADORES JUNTO CON LAS VISTAS DE LOS SERVICIOS
	 */
	public void genCRUD(){
		ExecuteShellComand obj= new ExecuteShellComand();
		String comando="bash "+path_proyect+"crud.sh "+path_proyect;
		List <Tabla>tablas=modelo.getTablas();
		obj.executeCommand(comando);
		//BORRAR BASH
		
		//CONFIGURACION DEL CONTROLADOR SERVICIOS / PERMISOS DE ACCESO
		for(Tabla tabla : tablas){
			try {
				System.out.println("CONTROLADOR ::"+tabla.getNombre());
				PHP_CRUD crud = new PHP_CRUD(tabla, modelo, path_proyect+"proyect/controllers/");
				crud.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//obj.move(path_proyect+"*Controller.php" , path_proyect+"proyect/controllers/"); //MUEVE LOS CONTROLADOR GENERADOR A LA CARPETA DE CONTROLADORES
		
		
		//LOAD PLUGINS O EXTECIONES PARA FORMULRIOS
		for(Tabla tabla : modelo.getTablas()){
			
			try {
				PHP_ViewForm form = new PHP_ViewForm(path_proyect+"proyect/views/"+tabla.getNombre().toLowerCase()+"/", tabla, modelo);
				form.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//GENERA LOS MODELOS PARA LAS VISTAS
	public void genModelView(){
		ExecuteShellComand obj= new ExecuteShellComand();
		List <View> views=modelo.getViews();
		//ESCRITURA DEL PHP CON EL MODELO DE LAS VISTAS
		for(View view : views) {
			try {
				PHP_ModelView model_view=new PHP_ModelView(view, modelo.getTabla(view.getTabla()), path_proyect);
				model_view.Write();//ESCRIBE EL MODELO DE UNA VISTA
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		obj.move(path_proyect+"*view.php" , path_proyect+"proyect/models/");
	}
	
	public void genCRUDView(){
		ExecuteShellComand obj= new ExecuteShellComand();
		List <View> views=modelo.getViews();
		
		//ESCRITURA DEL PHP CON EL MODELO DE LAS VISTAS
		for(View view : views) {
			try {
				//genCRUDView_(view);//ESCRIBE EL CRUD PARA LA VISTA
				PHP_CRUDView curd_view= new PHP_CRUDView(view, modelo, path_proyect);
				curd_view.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//GENERO UN MODELO PARA UNA VISTAS DE LA BDD
		}
		obj.move(path_proyect+"*Controller.php" , path_proyect+"proyect/controllers/");
	}
	
	public void execPermisos(){
		ExecuteShellComand obj= new ExecuteShellComand();
		obj.executeCommand("chmod 777 -R "+path_proyect);
	}
		
	//GENERA LAS VISTAS POR DEFECTO
	/*
	 * index
	 * about
	 * contact
	 */
	public void genViewsDefault(){
		PHP_Views views_default= new PHP_Views(web_name);
		try {
			 views_default.index();
			 views_default.about();
			 views_default.contact();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Carga configuración del sitio web por defecto (Tabla : Dashboard-conf)
	public void configureWeb(){
		//INSERTAR CONFIGURACION
		try {
			SQLite.insertConfWeb(web_name, " ", " ", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ESCRITURA DEL LAYOUTS DE LA CONFIGURACIÓN
		PHP_Layouts layouts = new PHP_Layouts(web_name, path_proyect+"proyect/views/layouts/main.php");
		try {
			layouts.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void permisosCRUD(){
		SQLite sqlite = new SQLite();
		try {
			sqlite.insertPermisos(modelo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}