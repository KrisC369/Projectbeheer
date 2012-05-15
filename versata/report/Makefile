all: report.pdf

report.pdf: MerodeToVersata.tex *.tex *.tex
	pdflatex MerodeToVersata.tex
	pdflatex MerodeToVersata.tex
view: 
	make all
	evince MerodeToVersata.pdf &
clean:
	-rm *.aux
	-rm *.log
	-rm *.out
	-rm *.toc
force: 
	touch MerodeToVersata.tex
	make clean
	make all
fullclean: clean
	-rm *.pdf
.PHONY: clean fullclean view force
