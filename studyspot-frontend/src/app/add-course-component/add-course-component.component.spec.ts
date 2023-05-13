import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCourseComponentComponent } from './add-course-component.component';

describe('AddCourseComponentComponent', () => {
  let component: AddCourseComponentComponent;
  let fixture: ComponentFixture<AddCourseComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCourseComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCourseComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
