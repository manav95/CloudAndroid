import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MediaCapture, MediaFile, CaptureError, CaptureImageOptions, CaptureVideoOptions } from '@ionic-native/media-capture';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, public mediaCapture: MediaCapture) {

  }

  startVideo(event) {
     console.log(event)
     let options: CaptureVideoOptions = { limit: 1 };
     this.mediaCapture.captureVideo(options).then(
         (data: MediaFile[]) => console.log(data),
         (err: CaptureError) => console.error(err)
     );

  }

}
