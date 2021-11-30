import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Account } from 'src/app/model/account.model';

import { Operation } from 'src/app/model/operation.model';

import { AccountService } from 'src/app/services/account.service';
import { OperationService } from 'src/app/services/operations.service';


@Component({
  selector: 'app-operations-create',
  templateUrl: './operations-create.component.html',
  styleUrls: ['./operations-create.component.css']
})
export class CreateOperationsComponent implements OnInit {



  idAccount:number;

  editForm = this.formBuilder.group({

    ammount: [],
    type: [Validators.required],
    date: [],

  });

  constructor(public operationService :OperationService,public route: ActivatedRoute,private formBuilder:FormBuilder){
      
  }

  ngOnInit(){
    this.idAccount = this.route.snapshot.params.id;
    console.log(this.idAccount)
    }

    save(): void {
      console.log('save lunched');
      console.log(this.createFromForm());
      this.operationService.create(this.idAccount,this.createFromForm()).subscribe(operations=>{
           this.previousState();
        });
    }

    private createFromForm(): Operation {
      return {
        ...new Operation(),
        amount: this.editForm.get(['ammount'])!.value,
        type: this.editForm.get(['type'])!.value,
      };
    }

    previousState(): void {
      window.history.back();
    }
  
}
