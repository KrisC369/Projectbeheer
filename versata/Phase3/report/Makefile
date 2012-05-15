all: report.pdf

report.pdf: SAProject3.tex *.tex *.tex
	pdflatex SAProject3.tex
	pdflatex SAProject3.tex
view: 
	make all
	evince SAProject3.pdf &
clean:
	-rm *.aux
	-rm *.log
	-rm *.out
	-rm *.toc
force: 
	touch SAProject3.tex
	make clean
	make all
fullclean: clean
	-rm *.pdf
.PHONY: clean fullclean view force
