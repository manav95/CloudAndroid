import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { MediaCapture, MediaFile, CaptureError, CaptureImageOptions } from '@ionic-native/media-capture';
import { TabsPage } from '../pages/tabs/tabs';
import { Storage } from '@ionic/storage';
import { ContactService } from '../services/contact.service';
import { AndroidPermissions } from '@ionic-native/android-permissions';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage:any = TabsPage;

  constructor(
      platform: Platform,
      statusBar: StatusBar,
      splashScreen: SplashScreen,
      ourStorage: SQLite,
      private androidPermissions: AndroidPermissions, private contactService: ContactService, private sqlite: SQLite ) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      splashScreen.show();
      statusBar.styleDefault();
      splashScreen.hide();
      ourStorage = sqlite;

      androidPermissions.checkPermission(androidPermissions.PERMISSION.CAMERA).then(
          success => console.log('Permission Granted'),
          err => androidPermissions.requestPermission(androidPermissions.PERMISSION.CAMERA)
      );

      androidPermissions.requestPermissions([androidPermissions.PERMISSION.CAMERA, androidPermissions.PERMISSION.GET_ACCOUNTS]);

      storage.set('name', 'Max');
      this.ourStorage.create({name: 'contact.db', location: 'default'}).then((db: SQLiteObject)=> {
        console.log(db);
        this.dbLocal = db;
    }).then(() => {
        this.contactService.setDB(this.dbLocal);
        this.dbLocal.executeSql('CREATE TABLE IF NOT EXISTS contacts(id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT, firstName TEXT, lastName TEXT, description TEXT)', {}).then(console.log('Contacts loaded'));
      document.addEventListener('pendingcaptureresult', function(mediaFiles) {
         console.log(mediaFiles)
       });

      // pendingcaptureerror is fired if the capture call is unsuccessful
      document.addEventListener('pendingcaptureerror', function(error) {
        console.log(error)
      });
    });
  }
}
