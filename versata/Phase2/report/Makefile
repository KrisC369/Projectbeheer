all: report.pdf

report.pdf: SAProject2.tex *.tex *.tex
	pdflatex SAProject2.tex
	pdflatex SAProject2.tex
view: 
	make all
	evince SAProject2.pdf &
clean:
	-rm *.aux
	-rm *.log
	-rm *.out
	-rm *.toc
force: 
	touch SAProject2.tex
	make clean
	make all
fullclean: clean
	-rm *.pdf
.PHONY: clean fullclean view force
