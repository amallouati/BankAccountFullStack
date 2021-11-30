import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/model/account.model';

import { Operation } from 'src/app/model/operation.model';

import { AccountService } from 'src/app/services/account.service';


@Component({
  selector: 'app-all-account',
  templateUrl: './account-bank.component.html',
  styleUrls: ['./account-bank.component.css']
})
export class AllAccountComponent implements OnInit {

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
