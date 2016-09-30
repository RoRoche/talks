INPUT=README.md
OUTPUT=talk_android_full_stack_rest.pdf
THEME=metropolis
LISTINGS=listings_setup.tex

$(OUTPUT): $(INPUT)
	pandoc -st beamer -V theme:$(THEME) $(INPUT) --listings -H $(LISTINGS) -o $(OUTPUT)

run: $(OUTPUT)
	open $(OUTPUT)

clean:
	rm -f $(OUTPUT)