<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::any('/', 'IndexController');

Route::get('log/{message}', function ($message) {

    Log::useDailyFiles(storage_path() . '/logs/anotherLogFile.log');

    Log::info($message);

    return $message;
});

Route::get('log2/{message}', function ($message) {

//    Log::useDailyFiles(storage_path() . '/logs/anotherLogFile.log');

    Log::info($message);

    return $message;
});