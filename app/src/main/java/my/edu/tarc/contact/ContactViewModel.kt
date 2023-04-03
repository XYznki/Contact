package my.tarc.mycontact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactViewModel (application: Application): AndroidViewModel(application) {
    //LiveData gives us updated contacts when they change
    var contactList : LiveData<List<Contact>>
    private val repository: ContactRepository

    init {
        //Get DB instance return DAO
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        //Connect DAO to Repo
        repository = ContactRepository(contactDao)
        //Pass data copy to VM
        contactList = repository.allContacts
    }

    fun addContact(contact: Contact) = viewModelScope.launch{
         repository.add(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch {
        repository.update(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)
    }



}
