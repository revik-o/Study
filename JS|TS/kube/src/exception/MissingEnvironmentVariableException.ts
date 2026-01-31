export class MissingEnvironmentVariableException extends Error {
    public constructor(variableName: string) {
        super(`Environment variable '${variableName}' is missing`);
        this.name = 'MissingEnvironmentVariableException';
    }
}
