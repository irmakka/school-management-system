package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.StudentMapper;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.DTO.RegisterDTO;

@Service
public class StudentService {

 private final StudentRepository stRep;
 private final StudentMapper studentMap;
 private final PasswordEncoder passwordEncoder;
 private final UserService userService;

 public StudentService(StudentRepository studentRep,
         StudentMapper studentMapper,
         PasswordEncoder passwordEncoder,
         UserService userService) {
this.stRep = studentRep;
this.studentMap = studentMapper;
this.passwordEncoder = passwordEncoder;
this.userService = userService;
}

public List<StudentDTO> getByStudentClasses(String classNo){
	 List<Student> students=stRep.findByClassNo(classNo);
	 if(students.isEmpty()) {
		 throw new ResourceNotFoundException("There is no student in this class:"+ classNo);
	 }
	 else {
	  return students.stream().map(studentMap::mapStudentToStudentDTO).collect(Collectors.toList());
	 }
}
public StudentDTO saveStudent(RegisterDTO registerDTO) {

	if (stRep.findByEmail(registerDTO.getEmail()).isPresent()) {
        throw new IllegalArgumentException(
                "This email already exists: " + registerDTO.getEmail()
        );
    }

    Student student = studentMap.toEntity(registerDTO);
    student.setPassword(passwordEncoder.encode(student.getPassword()));

    Student savedStudent = stRep.save(student);
    userService.saveUser(
    	    registerDTO.getEmail(),
    	    registerDTO.getPassword(),
    	    "STUDENT"
    	);

    return studentMap.mapStudentToStudentDTO(savedStudent);
}

public StudentDTO getMyStudent(String email) {
    return stRep.findByEmail(email)
            .map(studentMap::mapStudentToStudentDTO)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "There is no student with this email: " + email));
}
public List<StudentDTO> getAllStudents(){
	List<Student> students= stRep.findAll();
	if(students.isEmpty()) {
	 throw new ResourceNotFoundException("There is no students recorded");
	}
	
	return stRep.findAll().stream().map(studentMap::mapStudentToStudentDTO).collect(Collectors.toList());
}

public StudentDTO getAStudent(Long studentId){
	StudentDTO studentDTO= stRep.findById(studentId).map(studentMap::mapStudentToStudentDTO).orElseThrow( () -> new
	ResourceNotFoundException("There is no student with this id: " + studentId));
	
	return studentDTO;
}

public String  deleteStudent(Long studentId){
	if (stRep.findById(studentId).isEmpty()) {
		throw new ResourceNotFoundException("Student couldn't be found");
	}
	stRep.deleteById(studentId);
	return "deleted";
	 
}
 
}





