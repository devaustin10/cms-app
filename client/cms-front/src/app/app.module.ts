import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ModalComponent } from './components/modal/modal.component';
import { ContactListComponent } from './contact/contact-list/contact-list.component';
import { ContactDetailComponent } from './contact/contact-detail/contact-detail.component';
import { ContactEditComponent } from './contact/contact-edit/contact-edit.component';
import { ContactCreateComponent } from './contact/contact-create/contact-create.component';
import { LoaderComponent } from './shared/loader/loader.component';
import { AlertComponent } from './shared/alert/alert.component';
import { PaginationComponent } from './shared/pagination/pagination.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // Define routes here
  { path: '', redirectTo: '/contacts', pathMatch: 'full' }, // redirect to '/contact' by default
  { path: 'contacts', component: ContactListComponent }, // route for the Contact List
  { path: 'contact/:id', component: ContactDetailComponent } // route for the Contact Detail w/ a param for the contact ID
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ModalComponent,
    ContactListComponent,
    ContactDetailComponent,
    ContactEditComponent,
    ContactCreateComponent,
    LoaderComponent,
    AlertComponent,
    PaginationComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) // Add RouterModule with routes here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
