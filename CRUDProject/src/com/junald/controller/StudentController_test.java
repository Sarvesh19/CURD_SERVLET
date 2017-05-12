package com.junald.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.junald.dao.StudentDAO;
import com.junald.dao.StudentDAOImplementation;
import com.junald.model.Student;

@WebServlet("/StudentController_1")
public class StudentController_test extends HttpServlet {
	
	private StudentDAO dao;
	private static final long serialVersionUID = 1L;
	public static final String lIST_STUDENT = "/listStudent.jsp";
	public static final String INSERT_OR_EDIT = "/student.jsp";
       
    public StudentController_test() {
    	dao = new StudentDAOImplementation();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter( "action" );
		// request.getSession().invalidate(); if you want to destroy session
		if( action.equalsIgnoreCase( "delete" ) ) {
			forward = lIST_STUDENT;
			int studentId = Integer.parseInt( request.getParameter("studentId") );
			dao.deleteStudent(studentId);
			request.setAttribute("students", dao.getAllStudents() );
		}
		else if( action.equalsIgnoreCase( "edit" ) ) {
			forward = INSERT_OR_EDIT;
			int studentId = Integer.parseInt( request.getParameter("studentId") );
			Student student = dao.getStudentById(studentId);
			request.setAttribute("student", student);
		}
		else if( action.equalsIgnoreCase( "insert" ) ) {
			forward = INSERT_OR_EDIT;
		}
		else {
			forward = lIST_STUDENT;
			request.setAttribute("students", dao.getAllStudents() );
		}
		RequestDispatcher view = request.getRequestDispatcher( forward );
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student();
		student.setFirstName( request.getParameter( "firstName" ) );
		student.setLastName( request.getParameter( "lastName" ) );
		student.setCourse( request.getParameter( "course" ) );
		student.setYears(  request.getParameter( "years" )  );

		if(request.getParameter("studentId") != null){
			student.setStudentId(Integer.parseInt(request.getParameter("studentId")));

		}

		student.setStudentId(Integer.parseInt( request.getParameter("studentId") ));
		
		if( student.getStudentId() != null &&  student.getStudentId()>0) 
			dao.addStudent(student);
		else {
			student.setStudentId( student.getStudentId() );
			dao.updateStudent(student);
		}
		RequestDispatcher view = request.getRequestDispatcher( lIST_STUDENT );
		request.setAttribute("students", dao.getAllStudents());
		view.forward(request, response);
	}
}
