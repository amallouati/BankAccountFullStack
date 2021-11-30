import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllAccountComponent } from './components/views/account-bank/account-bank.component';

export const routes: Routes = [
  {
    path: '',
    component: AllAccountComponent
  },
  {
    path: 'operations',
    loadChildren: () => import('./components/views/operations/operations.module').then(m => m.GestionOperationsModule),
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
