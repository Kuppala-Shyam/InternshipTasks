import { Component, TemplateRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { todo_list } from '../class/todo';
import { CommonModule } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TodoService } from '../service/todo.service';

@Component({
  selector: 'app-todo',
  standalone: true, 
  imports: [FormsModule, CommonModule],
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css'],
})
export class TodoComponent {
  todoValue: string = '';
  currentDateTime: string = ''; // Property to hold the current date and time
  inputWarning: boolean = false; // Property to control warning display

  constructor(public todoService: TodoService, private modalService: NgbModal) {
    // Initialize current date and time
    this.updateDateTime();
  }

  // Function to update current date and time
  updateDateTime() {
    const currentDate = new Date();
    this.currentDateTime = currentDate.toLocaleString(); // Format date and time as a string
  }

  // Function to handle changing todo status
  changeTodo(i: number) {
    this.todoService.changeTodo(i);
  }

  // Function to add a new todo
  addTodo() {
    const success = this.todoService.addTodo(this.todoValue);
    if (!success) {
      // Show warning if input is empty
      this.inputWarning = true;
      return; // Exit function early if input is empty
    }
    // Reset warning
    this.inputWarning = false;
    this.todoValue = ''; // Clear input after adding todo item
  }

  // Function to handle changing finished status
  changeFinished(i: number) {
    this.todoService.changeFinished(i);
  }

  // Function to open modal for editing todo or finished list
  openModal(content: TemplateRef<Element>, i: number, type: String) {
    this.todoService.openModal(content, i, type);
  }
}
