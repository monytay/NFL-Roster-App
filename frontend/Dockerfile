FROM node:20-alpine

WORKDIR /app

# Copy dependency files first for better Docker layer caching
COPY package.json package-lock.json ./

# Install dependencies
RUN npm ci

# Copy the rest of the frontend source
COPY . .

# Vite default dev port
EXPOSE 5173

# Run Vite so it is reachable from outside the container
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
