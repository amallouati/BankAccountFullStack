import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/model/account.model';

import { Operation } from 'src/app/model/operation.model';

import { AccountService } from 'src/app/services/account.service';


@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent {

  @Input() ammount
  constructor(){

  }
}
