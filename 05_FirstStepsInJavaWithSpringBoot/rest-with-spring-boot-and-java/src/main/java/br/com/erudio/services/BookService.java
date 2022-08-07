
package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repository.BookRepository;

@Service
public class BookService {

	private Logger logger = Logger.getLogger(BookService.class.getName());

	@Autowired
	BookRepository bookRepository;

	public List<BookVO> findAll() {
		logger.info("Finding all book!");
		
		var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
		books.stream().forEach(
				book -> {
					try {
						book.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
		return books;
	}

	public BookVO findById(Long id) throws Exception {

		logger.info("Finding one book!");
		var entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	public BookVO create(BookVO book) throws Exception {

		if(book == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one book!");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

//	public BookVOV2 createV2(BookVOV2 book) {
//		logger.info("Creating one book with V2!");
//		var entity = bookMapper.convertVoToEntity(book);
//		var vo = bookMapper.convertEntityToVo(bookRepository.save(entity));
//		return vo;
//	}

	public BookVO update(BookVO book) throws Exception {

		if(book == null) throw new RequiredObjectIsNullException();
		logger.info("Updating one book!");
		var entity = bookRepository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {

		logger.info("Deleting one book!");
		var entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		bookRepository.delete(entity);
	}

}
