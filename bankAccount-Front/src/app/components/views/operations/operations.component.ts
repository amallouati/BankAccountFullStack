import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Account } from 'src/app/model/account.model';

import { Operation } from 'src/app/model/operation.model';

import { AccountService } from 'src/app/services/account.service';
import { OperationService } from 'src/app/services/operations.service';


@Component({
  selector: 'app-operations-account',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent implements OnInit {


  listOperation: Operation[] =[];
  idAccount:number;
  constructor(public operationService :OperationService,public route: ActivatedRoute){

  }

  ngOnInit(){
    console.log(this.route.snapshot.params)
    this.idAccount = this.route.snapshot.params.id;
  
    this.operationService.findAllOperationsByIdAccount(this.idAccount).subscribe(operations=>{
    this.listOperation=operations;
    console.log(this.listOperation)
    });
    }

    previousState(): void {
      window.history.back();
    }
}
