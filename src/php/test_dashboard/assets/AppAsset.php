<?php
/**
 * @link http://www.yiiframework.com/
 * @copyright Copyright (c) 2008 Yii Software LLC
 * @license http://www.yiiframework.com/license/
 */

namespace app\assets;

use yii\web\AssetBundle;

/**
 * @author Qiang Xue <qiang.xue@gmail.com>
 * @since 2.0
 */
class AppAsset extends AssetBundle
{
    public $basePath = '@webroot';
    public $baseUrl = '@web';
    public $css = [
        'css/site.css',
		'css/bootstrap.min.css',
		'css/font-awesome.min.css',
		'css/metisMenu.min.css',
		'css/sb-admin-2.css',
		'css/timeline.css',
    ];
    public $js = [
		'js/bootstrap.min.js',
		'js/jquery.min.js',
		'js/metisMenu.min.js',
		'js/morris.min.js',
		'js/morris-data.js',
		'js/raphael-min.js',
		'js/sb-admin-2.js',
    ];
    public $depends = [
        'yii\web\YiiAsset',
        'yii\bootstrap\BootstrapAsset',
    ];
}
