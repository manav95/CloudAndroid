import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewContactPage } from "../new-contact/new-contact";
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

export class Person {
  protected first_name: string;
  protected last_name: string;
  constructor(first_name: string, last_name:string){
    this.first_name = first_name;
    this.last_name = last_name;
  }
}

export class Contact extends Person {
  protected active: boolean;
  protected icon_url: string;
  constructor(first_name: string, last_name: string,icon_url: string, active: boolean){
    super(first_name,last_name);
    this.icon_url = icon_url;
    this.active = active;
  }
}

const contactsList: Contact[] = [
 new Contact('Fernando', 'Icaza', 'm1.png', true),
 new Contact('Bernie', 'Sanders', 'm2.png', true)
];


@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {
  contactsList = contactsList;
  ourStorage: SQLite;
  value: number;
  dbLocal: SQLiteObject;
  testingText = "something";
  constructor(public navCtrl: NavController, private sqlite: SQLite) {
    this.ourStorage = sqlite;
    let c1 = new Contact('Fernando','Icaza','m1.png', true);
    console.log('c1');
    console.log(c1);
    this.ourStorage.create({name: 'contact.db', location: 'default'}).then((db: SQLiteObject)=> {
      console.log(db);
      this.dbLocal = db}).then(() =>
      this.dbLocal.executeSql('CREATE TABLE IF NOT EXISTS contacts(id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT, firstName TEXT, lastName TEXT, description TEXT)', {}).then((data: any)=> {
             //this.add("Ferndie", "Fernando", "Icaza", "m1.png");
             //this.add("Labo", "Bernie", "Sanders", "m2.png");
             
      }).then(() => this.populateContactList()).catch((e) => console.log(e)))
  }

  public add(nick: String, first: String, last: String, desc: String) {
    this.dbLocal.executeSql("INSERT INTO contacts(nickname, firstName, lastName, description) VALUES (?, ?, ?, ?)", ["Ferndie", "Fernando", "Icaza", "m1.png"]).then((data) => {
        console.log("INSERTED: " + JSON.stringify(data));
    }, (error) => {
        console.log("ERROR: " + JSON.stringify(error.err));
    });
}

onClick(event: any) {
    console.log("Clicked button");
    // alert('clicked button');
    this.navCtrl.push(NewContactPage);
}

public populateContactList() {
    this.dbLocal.executeSql("SELECT id, nickname, firstName, lastName, description from contacts", []).then((data: any)=>console.log(JSON.stringify(data)));
}

  // console.log(this.contactsList);
}
