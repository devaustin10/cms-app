import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient} from '@angular/common/http';
import { Contact } from "./contact.model";

// Decorator indicating that this class can be injectd into other components or services
// Decorators are applied to classes, methods, properties, and other members to add metadata or modify behavior. 
@Injectable({
    providedIn: 'root'
})
export class ContactService {
    private apiUrl = 'http://localhost:8080'; // Replace with backend API

    // HttpClient dependency injected in order to perform HTTP requests 
    constructor(private http: HttpClient) { }

    // takes contactData and return Observable<any>
    addContact(contactData: any): Observable<any> {
        // constructing the url
        const url = `${this.apiUrl}/contacts`;
        // making a post http request w/ the contactData as the request body info
        // returns an observable that represents the async operation of sending the data to the server
        return this.http.post(url, contactData);
    }

    getAllContacts(): Observable<Contact[]> {
        return this.http.get<Contact[]>(`${this.apiUrl}/contacts`);
    }

    getContactById(id: number): Observable<Contact> {
        return this.http.get<Contact>(`${this.apiUrl}/contacts/${id}`);
    }

    deleteContact(id: number): Observable<void> {
        const url = `${this.apiUrl}/contacts/${id}`;
        return this.http.delete<void>(url);
    }

    updateContact(updatedContact: Contact): Observable<Contact> {
        const url = `${this.apiUrl}/contacts/${updatedContact.contactId}`;
        return this.http.put<Contact>(url, updatedContact);
    }

}