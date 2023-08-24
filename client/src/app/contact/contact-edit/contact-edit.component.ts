import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ContactService } from '../contact.service';
import { Contact } from '../contact.model';

@Component({
  selector: 'app-contact-edit',
  templateUrl: './contact-edit.component.html'
})
export class ContactEditComponent implements OnInit {
  editForm!: FormGroup;
  contact!: Contact;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private contactService: ContactService
  ) {}

    ngOnInit(): void {
      this.route.params.subscribe(params => {
        const contactId = params['id'];
        this.loadContactDetails(contactId);
      });

      this.editForm = this.formBuilder.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        phoneNumber: ['', Validators.required]
      });
    }

    loadContactDetails(contactId: number): void {
      this.contactService.getContactById(contactId).subscribe(contact => {
        this.contact = contact;
        this.editForm.patchValue({
          firstName: contact.firstName,
          lastName: contact.lastName,
          email: contact.email,
          phoneNumber: contact.phoneNumber
        });
      });
    }

    onSubmit(): void {
      if (this.editForm.invalid) {
        return;
      }
      
      const updatedContact: Contact = {
        ...this.contact,
        ...this.editForm.value
      };

      this.contactService.updateContact(updatedContact).subscribe({
        next: () => {
          console.log('Contact updated successfully');
          this.router.navigate(['/contacts', this.contact.contactId]);
        },
        error: (error) => {
          console.error('Error updating contact: ', error);
        }
      });
    }

}
