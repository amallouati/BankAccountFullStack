import { Component, OnInit } from '@angular/core';
import { Account } from './model/account.model';
import { Operation } from './model/operation.model';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Account';
  accountList: Account[] =[];
  constructor(public accountService :AccountService){

  }

  ngOnInit(){
    console.log('init component');
    this.accountService.getAllAccount().subscribe(accouts=>{
      console.log(accouts);
      this.accountList=accouts;
    });
    }
}

