import { Component } from '@angular/core';
import {Validators, FormBuilder, FormGroup } from '@angular/forms';
import {Storage} from '@ionic/storage';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';
/**
 * Generated class for the CreateContactComponent component.
 *
 * See https://angular.io/docs/ts/latest/api/core/index/ComponentMetadata-class.html
 * for more info on Angular Components.
 */
@Component({
  selector: 'create-contact',
  templateUrl: 'create-contact.html'
})
export class CreateContactComponent {
  private newContactEntry : FormGroup;
  text: string;
  ourStorage: SQLite;
  builder: FormBuilder;
  value: number;
  dbLocal: SQLiteObject;
  constructor( private formBuilder: FormBuilder, private sqlite: SQLite ) {
    this.ourStorage = sqlite;

    console.log('\nHello CreateContactComponent Component\n');

    this.text = 'Fill out the form to add Friends to your list';
    this.builder = formBuilder;
    let length = 0;
    this.newContactEntry = formBuilder.group({
      id: length + 1,
      nickname: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      description: [''],
    });
  }

  logForm () {
     console.log(this.newContactEntry.value);
     this.queryLanguage(this.newContactEntry.value.id, this.newContactEntry.value.nickname,
        this.newContactEntry.value.firstName,this.newContactEntry.value.lastName,
        this.newContactEntry.value.description)

  }

  queryLanguage(id: number, nickname: string, firstName: string, lastName: string, description: string) {
      this.dbLocal.executeSql('insert into contacts values (?, ?, ?, ?, ?)',
       [id, nickname, firstName, lastName, description])
  }
}
