all: report.pdf

report.pdf: SAProject1.tex *.tex *.tex
	pdflatex SAProject1.tex
	pdflatex SAProject1.tex
view: 
	make all
	evince SAProject1.pdf &
clean:
	-rm *.aux
	-rm *.log
	-rm *.out
force: 
	touch SAProject1.tex
	make clean
	make all
fullclean: clean
	-rm *.pdf

.PHONY: clean fullclean view force
