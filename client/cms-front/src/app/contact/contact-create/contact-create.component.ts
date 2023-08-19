import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-contact-create',
  templateUrl: './contact-create.component.html'
})
export class ContactCreateComponent implements OnInit {
  contactForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private contactService: ContactService) {

  }

  ngOnInit(): void {
    this.contactForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required]
    })
  }

  onSubmit() {
    if (this.contactForm.invalid) {
      return;
    }

    const newContact = this.contactForm.value;

    // call the addContact method and subscribe to the returned observable to handle the response or errors
    // subscribe method allows you to observe the observables and execute the provided callback functions 
    // when the data is successfully return or when an error occurs
    // subscribe method expects an object w/ callback functions to be provided as its argument
    this.contactService.addContact(newContact).subscribe({
      // next and error functions are callback functions b/c passed to the subscribe method
      next: (response) => {
        // Handle success (e.g., show a success message)
        console.log('Contact added successfully:', response);
      },
      error: (error) => {
        // Handle error (e.g., show an error message)
        console.error('Error adding contact:', error);
      }
  });
  }


}
