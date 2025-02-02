<?php

namespace app\models;

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use dosamigos\ckeditor\CKEditor;
use yii\helpers\ArrayHelper;

/* @var $this yii\web\View */
/* @var $model app\models\Views */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="views-form">
  <?php
    $url="index.php?r=view-advance/index&id=$model->id_view";
    echo "<a href=\"$url\" class=\"btn btn-info\" role=\"button\">Advance Settings</a>"; ?>

    <p><p>

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'title')->textInput(['maxlength' => true]) ?>

    <?php echo $form->field($model, 'id_rol')->dropDownList(ArrayHelper::map(Roles::find()->all(), 'id_rol', 'rolname'), ['id'=>'id_rol']); ?>


    <?= $form->field($model, 'content')->widget(CKEditor::className(), [
        'options' => ['rows' => 6],
        'preset' => 'full'
    ]) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
