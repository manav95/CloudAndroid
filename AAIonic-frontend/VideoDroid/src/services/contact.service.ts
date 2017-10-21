import { Injectable } from '@angular/core';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

@Injectable()
export class ContactService {
   public dbLocal: SQLiteObject;
   public ourStorage: SQLite;
   constructor() {

   }

   setDB(db: SQLiteObject) {
       this.dbLocal = db;
   }
   getDB(): SQLiteObject {
       return this.dbLocal;
   }

}
