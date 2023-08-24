import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { ContactListComponent } from "./contact/contact-list/contact-list.component";
import { ContactDetailComponent } from "./contact/contact-detail/contact-detail.component";
import { ContactCreateComponent } from "./contact/contact-create/contact-create.component";
import { ContactEditComponent } from "./contact/contact-edit/contact-edit.component";

const routes: Routes = [
    // Define routes here
    { path: '', redirectTo: '/contacts', pathMatch: 'full' }, // redirect to '/contact' by default
    { path: 'contacts', component: ContactListComponent, pathMatch: 'full' }, // route for the Contact List
    { path: 'contacts/add', component: ContactCreateComponent },
    { path: 'contacts/:id', component: ContactDetailComponent, pathMatch: 'full', data: { id: 'number' } }, // route for the Contact Detail w/ a param for the contact ID
    { path: 'contacts/:id/edit', component: ContactEditComponent, pathMatch: 'full', data: { id: 'number' } }
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)], // Add RouterModule with routes here
    exports: [RouterModule]
  })
  
  export class AppRoutingModule {

  }