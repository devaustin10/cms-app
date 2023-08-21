import { Component } from '@angular/core';
import { ContactService } from '../contact.service';


@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html'
})
export class ContactListComponent {
  // array of contacts
  contacts: any[] = [];

  constructor(private contactService: ContactService) { }

  ngOnInIt(): void {
    this.loadContacts();
  }

  loadContacts(): void {
    this.contactService.getAllContacts().subscribe({
      next: (contacts) => {
        this.contacts = contacts;
      },
      error: (error) => {
        console.error('Error fetching contacts: ', error);
      }
    });
  }
  
}
