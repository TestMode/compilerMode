<?php
namespace app\models;

//use kartik\grid\GridView; //new
use Yii;
use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */
if(!Yii::$app->user->isGuest && Yii::$app->user->identity->id_rol == 1){
  $this->title = 'Dashboard Permisoscruds';
  $this->params['breadcrumbs'][] = $this->title;
?>
  <div class="dashboard-permisoscrud-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <a class="btn btn-success" href="index.php?r=dashboard-permisoscrud/create">Create Dashboard Permisoscrud</a>
    </p>


    <div id="w0" class="grid-view"><div class="summary">Showing <b>1-20</b> of <b>35</b> items.</div>
      <table class="table table-striped table-bordered">
        <thead>
          <td>#</td>
          <td>Clase</td>
          <td>Ver</td>
        </thead>
        <tbody>
          <?php
          $results= DashboardPermisoscrud::find()->groupBy('id_dash')->all();
          $cont=1;
          foreach($results AS $model){
            //echo $model->id_dash;
            $dash=Dashboard::find()->select('nombre')->where(['id'=>$model->id_dash])->one();
            echo "<tr data-key=\"1\">\n
                    <td>$cont</td>\n
                    <td>$dash->nombre</td>\n
                    <td>";
                      $link="index.php?r=dashboard-permisoscrud/index&id=$model->id_dash";
                      echo "<a class=\"btn btn-primary\" href=\"$link\">ver</a>";
            echo"  </td>
                  </tr>";
            $cont++;
          }
          ?>
        </tbody>
      </table>
    </div>



<?php
}else{
 ?>
 <div class="site-error">

     <h1>Forbidden (#403)</h1>

     <div class="alert alert-danger">
         You are not Admin to perform this action.    </div>

     <p>
         The above error occurred while the Web server was processing your request.
     </p>
     <p>
         Please contact us if you think this is a server error. Thank you.
     </p>

 </div>
<?php
}
 ?>
