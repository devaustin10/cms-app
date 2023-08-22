import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContactService } from '../contact.service';
import { Contact } from '../contact.model';

@Component({
  selector: 'app-contact-detail',
  templateUrl: './contact-detail.component.html'
})
export class ContactDetailComponent {
  contact!: Contact; // Change the type as per you contact model

  constructor(private route: ActivatedRoute, private contactService: ContactService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const contactId = params['id'];
      this.loadContactDetails(contactId);
    });
  }

  loadContactDetails(contactId: number): void {
    this.contactService.getContactById(contactId).subscribe({
      next: (contact) => {
        this.contact = contact;
      },
      error: (error) => {
        console.error('Error fetching contact details: ', error);
      }
    });
  } 
}
