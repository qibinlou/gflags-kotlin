package gflags

internal object Parsers {
    val STRING_PARSER = StringArgumentParser()
    val BOOLEAN_PARSER = BooleanArgumentParser()
    val INTEGER_PARSER = IntegerArgumentParser()
    val FLOAT_PARSER = FloatArgumentParser()
}