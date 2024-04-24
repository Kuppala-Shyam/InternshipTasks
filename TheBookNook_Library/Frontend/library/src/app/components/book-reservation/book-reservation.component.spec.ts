import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookReservationsComponent } from './book-reservation.component';

describe('BookReservationComponent', () => {
  let component: BookReservationsComponent;
  let fixture: ComponentFixture<BookReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookReservationsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
