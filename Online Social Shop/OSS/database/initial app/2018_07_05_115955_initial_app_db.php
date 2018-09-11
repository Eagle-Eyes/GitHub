<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;
use Illuminate\Support\Facades\Artisan;
use Illuminate\Support\Facades\Log;
use App\User;

class InitialAppDb extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */

    var $log_path = '/logs/initial app.log';

    public function up()
    {

        Log::useDailyFiles(storage_path() . $this->log_path);

        Schema::create('Users', function (Blueprint $table) {
            $table->uuid('id', 50)->comment('Unique identification')->unique();
            $table->string('display_name', 50)->charset('utf8')->collation('utf8_unicode_ci')->comment('Display name of user');
            $table->string('email', 255)->charset('utf8')->collation('utf8_unicode_ci')->comment('Unique email of user');
            $table->string('password', 100)->charset('utf8')->collation('utf8_unicode_ci')->comment('Hashed password with');
            $table->timestamp('update_at')->comment('Update date of user');
            $table->dateTime('create_at')->comment('Create date of user');
//          $table->rememberToken();
        });
        
        Log::info('The table "Users" were created successfully.');

    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {

        Log::useDailyFiles(storage_path() . $this->log_path);

        Schema::dropIfExists('Users');
        Log::info('The table Users were dropped.');
    }
}
