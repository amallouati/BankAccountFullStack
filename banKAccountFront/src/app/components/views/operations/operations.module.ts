import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { BalanceComponent } from "../../shared/balance.component";
import { CreateOperationsComponent } from "./operations-create/operations-create.component";
import { OperationsComponent } from "./operations.component";


export const operationsRoute: Routes = [
    {
      path: ':id/view',
      component: OperationsComponent,
    },
    {
        path: ':id/new',
        component: CreateOperationsComponent,
      },
   
  ];

  @NgModule({
    imports: [CommonModule,ReactiveFormsModule,FormsModule, RouterModule.forChild(operationsRoute)],
    declarations: [OperationsComponent,CreateOperationsComponent],
    entryComponents: [],
  })
  export class GestionOperationsModule {}