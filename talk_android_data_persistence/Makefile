INPUT=README.md
OUTPUT=talk_android_data_persistence.pdf
THEME=metropolis
LISTINGS=listings_setup.tex

$(OUTPUT): $(INPUT)
	pandoc -st beamer -V theme:$(THEME) $(INPUT) --listings -H $(LISTINGS) -o $(OUTPUT)

run: $(OUTPUT)
	open $(OUTPUT)

clean:
	rm -f $(OUTPUT)