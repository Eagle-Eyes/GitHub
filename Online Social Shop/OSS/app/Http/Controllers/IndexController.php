<?php
/**
 * Created by PhpStorm.
 * User: b.heydarinejad
 * Date: 6/29/2018
 * Time: 10:39 PM
 */

namespace App\Http\Controllers;


class IndexController extends Controller
{
    public function __invoke()
    {
        return 'Index';
    }

}