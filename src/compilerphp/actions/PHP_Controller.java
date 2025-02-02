package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

/*
 * Generador Controlador
 * - carga los los roles para el control de los accesos a los servicios
 */
public class PHP_Controller{
	
	protected Tabla tabla;
	protected SQL modelo;
	protected String path_proyect;
	
	public PHP_Controller(Tabla tabla_, SQL modelo_, String path_proyect_){
		this.tabla=tabla_;
		this.modelo=modelo_;
		this.path_proyect=path_proyect_;
	}
	
	public void write() throws IOException{ {
		
			//DEBE USAR LA TABLA NO LA VISTA!, PARA LOS SERVIVIOS DEL CRUD
			//Tabla tabla=modelo.getTabla(view.getTabla());
			Atributo pk=tabla.getPrimaryKey();
			FileWriter php_crud_view = null;
			String controler="<?php\n\n";
			controler=controler+"\n";
			controler=controler+"namespace app\\controllers;\n";
			controler=controler+"\n";
			controler=controler+"use Yii;\n";
			controler=controler+"use app\\models\\"+this.tabla.getNombre()+";\n";
			controler=controler+"use app\\models\\"+this.tabla.getNombre()+"Search;\n";
			controler=controler+"use yii\\web\\Controller;\n";
			controler=controler+"use yii\\web\\NotFoundHttpException;\n";
			controler=controler+"use yii\\filters\\VerbFilter;\n";
			controler=controler+"\n";
			controler=controler+"use yii\\filters\\AccessControl;\n";
			controler=controler+"\n";
			controler=controler+"class "+this.tabla.getNombre()+"Controller extends Controller\n";
			controler=controler+"{\n";
			controler=controler+"		    protected $roles_= array();\n";
			controler=controler+"\n";
			controler=controler+"		    public function behaviors()\n";
			controler=controler+"		    {\n";
			controler=controler+"	        return [\n";
			controler=controler+"          'access'=>[\n";
			controler=controler+"              'class'=>AccessControl::className(),\n";
			controler=controler+"\n";
			controler=controler+"                  'rules'=>[\n";
			controler=controler+"                            [\n";
			controler=controler+"                                 'actions'=>[\n";
			controler=controler+"                                    'index'\n";
			controler=controler+"                                ],\n";
			controler=controler+"                                'allow'=>true,\n";
			controler=controler+"                                'matchCallback'=>function(){\n";
			controler=controler+"                                    if(!Yii::$app->user->isGuest){\n";
			controler=controler+"                                      return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'index'));\n";
			controler=controler+"                                    }\n";
			controler=controler+"                                    else{\n";
			controler=controler+"                                      false;\n";
			controler=controler+"                                    }\n";
			controler=controler+"                                }\n";
			controler=controler+"                            ],\n";
			controler=controler+"                            [\n";
			controler=controler+"                                'actions'=>[\n";
			controler=controler+"                                    'create'\n";
			controler=controler+"                                ],\n";
			controler=controler+"                                'allow'=>true,\n";
			controler=controler+"                                'matchCallback'=>function(){\n";
			controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
			controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'create'));\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                  else{\n";
			controler=controler+"                                    false;\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                }\n";
			controler=controler+"                            ],\n";
			controler=controler+"                            [\n";
			controler=controler+"                                'actions'=>[\n";
			controler=controler+"                                    'update',\n";
			controler=controler+"                                ],\n";
			controler=controler+"                                'allow'=>true,\n";
			controler=controler+"                                'matchCallback'=>function(){\n";
			controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
			controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'update'));\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                  else{\n";
			controler=controler+"                                    false;\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                }\n";
			controler=controler+"                            ],\n";
			controler=controler+"                            [\n";
			controler=controler+"                                'actions'=>[\n";
			controler=controler+"                                   'delete',\n";
			controler=controler+"                                ],\n";
			controler=controler+"                                'allow'=>true,\n";
			controler=controler+"                                'matchCallback'=>function(){\n";
			controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
			controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'delete'));\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                  else{\n";
			controler=controler+"                                    false;\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                }\n";
			controler=controler+"                            ],\n";
			controler=controler+"                            [\n";
			controler=controler+"                                'actions'=>[\n";
			controler=controler+"                                    'view'\n";
			controler=controler+"                                ],\n";
			controler=controler+"                                'allow'=>true,\n";
			controler=controler+"                                'matchCallback'=>function(){\n";
			controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
			controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'view'));\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                  else{\n";
			controler=controler+"                                    false;\n";
			controler=controler+"                                  }\n";
			controler=controler+"                                }\n";
			controler=controler+"                            ],\n";
			controler=controler+"                        ],\n";
			controler=controler+"                    ],\n";
			controler=controler+"		                  'verbs' => [\n";
			controler=controler+"		                      'class' => VerbFilter::className(),\n";
			controler=controler+"		                      'actions' => [\n";
			controler=controler+"		                          'delete' => ['post'],\n";
			controler=controler+"		                      ],\n";
			controler=controler+"		                  ],\n";
			controler=controler+"		              ];\n";
			controler=controler+"\n";
			controler=controler+"		    }\n";
			controler=controler+"\n";
			controler=controler+"		    public function actionIndex()\n";
			controler=controler+"		    {\n";
			controler=controler+"		        $searchModel = new "+this.tabla.getNombre()+"Search();\n";
			controler=controler+"		        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);\n";
			controler=controler+"\n";
			controler=controler+"		        return $this->render('index', [\n";
			controler=controler+"		            'searchModel' => $searchModel, \n";
			controler=controler+"		            'dataProvider' => $dataProvider,\n";
			controler=controler+"		        ]);\n";
			controler=controler+"		    }\n";
			controler=controler+"\n";
			controler=controler+"		    public function actionView($id)\n";
			controler=controler+"		    {\n";
			controler=controler+"		        return $this->render('view', [\n";
			controler=controler+"		            'model' => $this->findModel($id),\n";
			controler=controler+"		        ]);\n";
			controler=controler+"		    }\n";
			controler=controler+"\n";
			controler=controler+"		    public function actionCreate()\n";
			controler=controler+"		    {\n";
			controler=controler+"		        $model = new "+this.tabla.getNombre()+"();\n";
			controler=controler+"\n";
			controler=controler+"		        if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
			controler=controler+"		            return $this->redirect(['view', 'id' => $model->"+pk.getNombre()+"]);\n";
			controler=controler+"		        } else {\n";
			controler=controler+"		            return $this->render('create', [\n";
			controler=controler+"		                'model' => $model,\n";
			controler=controler+"		            ]);\n";
			controler=controler+"		        }\n";
			controler=controler+"		    }\n";
			controler=controler+"\n";
			controler=controler+"		    public function actionUpdate($id)\n";
			controler=controler+"		    {\n";
			controler=controler+"		        $model = $this->findModel($id);\n";
			controler=controler+"\n";
			controler=controler+"		        if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
			controler=controler+"		            return $this->redirect(['view', 'id' => $model->"+pk.getNombre()+"]);\n";
			controler=controler+"		        } else {\n";
			controler=controler+"		            return $this->render('update', [\n";
			controler=controler+"		                'model' => $model,\n";
			controler=controler+"		            ]);\n";
			controler=controler+"		        }\n";
			controler=controler+"		    }\n";
			controler=controler+"\n";
			controler=controler+"		    public function actionDelete($id)\n";
			controler=controler+"		    {\n";
			controler=controler+"		        $this->findModel($id)->delete();\n";
			controler=controler+"\n";
			controler=controler+"		        return $this->redirect(['index']);\n";
			controler=controler+"		    }\n";
			controler=controler+"\n";
			controler=controler+"		    protected function findModel($id)\n";
			controler=controler+"		    {\n";
			controler=controler+"			        if (($model = "+this.tabla.getNombre()+"::findOne($id)) !== null) {\n";
			controler=controler+"			            return $model;\n";
			controler=controler+"			        } else {\n";
			controler=controler+"			            throw new NotFoundHttpException('The requested page does not exist.');\n";
			controler=controler+"			        }\n";
			controler=controler+"		    }\n";
			controler=controler+"}\n";
			
		    //ESCRITURA DEL PHP CON EL MODELO DE LA VISTA
		    php_crud_view = new FileWriter(path_proyect+this.tabla.getNombre()+"Controller.php");
		    php_crud_view.write(controler);
		    php_crud_view.close();
		}
	}
}