import os
import bs4
from langchain import hub
from langchain.chat_models import ChatOpenAI
from langchain.document_loaders import WebBaseLoader, UnstructuredPDFLoader
from langchain.embeddings import OpenAIEmbeddings
from langchain.schema import StrOutputParser
from langchain.schema.runnable import RunnablePassthrough
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.vectorstores import Chroma

os.environ["OPENAI_API_KEY"] = "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"

def process_file(file_path):
    loader = UnstructuredPDFLoader(file_path)
    docs = loader.load()

    text_splitter = RecursiveCharacterTextSplitter(chunk_size=100, chunk_overlap=10)
    splits = text_splitter.split_documents(docs)

    vectorstore = Chroma.from_documents(documents=splits, embedding=OpenAIEmbeddings())
    retriever = vectorstore.as_retriever()

    prompt = hub.pull("rlm/rag-prompt")
    llm = ChatOpenAI(model_name="gpt-3.5-turbo", temperature=0)

    def format_docs(docs):
        return "\n\n".join(doc.page_content for doc in docs)

    rag_chain = (
        {"context": retriever | format_docs, "question": RunnablePassthrough()}
        | prompt
        | llm
        | StrOutputParser()
    )

    response = rag_chain.invoke("Dame un resumen del perfil profesional")

    return response

def process_files(input_file_path, output_file_path):
    with open(input_file_path, 'r') as file:
        file_paths = file.readlines()

    results = []

    for file_path in file_paths:
        file_path = file_path.strip().strip('"')  # Remove leading/trailing whitespaces, newlines, and quotes
        result = process_file(file_path)
        results.append(result)

    with open(output_file_path, 'w') as output_file:
        output_file.write('\n\n'.join(results))


# Example usage:
input_file_path = "C:\\Users\\David Castro\\OneDrive\\Documentos\\Maestría\\HiperAutomatización\\Casos de uso\\rutas-casos-uso.txt"  # Replace with the actual path of your input file
output_file_path = "C:\\Users\\David Castro\\OneDrive\\Documentos\\Maestría\\HiperAutomatización\\Casos de uso\\resultado.txt"  # Replace with the desired output file path

process_files(input_file_path, output_file_path)
