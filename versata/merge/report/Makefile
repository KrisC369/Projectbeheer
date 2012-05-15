all: report.pdf

report.pdf: SAProjectM.tex *.tex *.tex
	pdflatex SAProjectM.tex
	pdflatex SAProjectM.tex
view: 
	make all
	evince SAProjectM.pdf &
clean:
	-rm *.aux
	-rm *.log
	-rm *.out
	-rm *.toc
force: 
	touch SAProjectM.tex
	make clean
	make all
fullclean: clean
	-rm *.pdf
.PHONY: clean fullclean view force
